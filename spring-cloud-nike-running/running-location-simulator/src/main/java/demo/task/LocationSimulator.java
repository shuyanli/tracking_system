package demo.task;

import demo.model.*;
import demo.service.PositionService;
import demo.support.NavUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

//todo 这里不加data是因为我们不希望field都暴露给外界, 有安全隐患
public class LocationSimulator implements Runnable {

    @Getter
    @Setter //todo由上面那条, 对于我们必须要让其他人调用的field, 我们手动加上getter, setter
    private long id;
    private AtomicBoolean cancel = new AtomicBoolean();


    //todo 这里, positionService是一个interface, 但是我们没有像之前一样, 用@autowire的方法注入进来
    //因为, 这个interface伴随每一个LocationSimulator线程而创建一个属于thread的instance, 它不是singleton
    //所以不能用注入的方法导入
    //todo 这个positionService是一个interface, 为什么可以直接这么用?

    //答疑: 这个positionService只有一个instance, 但是locaitionSimulator有多个instance
    @Setter
    private PositionService positionService;

    private String runningId;
    private double speedInMps;
    private boolean shouldMove = true;
    private boolean exportPositionsToMessaging = true; //todo->管控是否向distribution发信息
    private Integer reportInternal;

    //todo 搞明白下面这些field都是怎么传进来的
    private RunnerStatus runnerStatus = RunnerStatus.NONE;
    private String polyline;
    private MedicalInfo medicalInfo;

    @Getter
    @Setter
    private PositionInfo positionInfo = null; //todo->线程模拟"移动"就靠positionInfo改变来改变状态

    @Setter
    private List<Leg> legs;  //todo 这个是怎么进来的

    @Setter
    private Point startPoint;
    private Date executionStartTime;



    //todo 这个constructor没有被call, 这些值怎么进来的
    public LocationSimulator(GpsSimulatorRequest gpsSimulatorRequest){
        this.runningId = gpsSimulatorRequest.getRunningId();
        this.setSpeed(gpsSimulatorRequest.getSpeed());
        this.shouldMove = gpsSimulatorRequest.isMove();
        this.exportPositionsToMessaging = gpsSimulatorRequest.isExportPositionsToMessaging();
        this.reportInternal = gpsSimulatorRequest.getReportInternal();
        this.runnerStatus = gpsSimulatorRequest.getRunnerStatus();
        this.polyline = gpsSimulatorRequest.getPolyline();
        this.medicalInfo = gpsSimulatorRequest.getMedicalInfo();
    }

    private void setSpeed(double speed){
        this.speedInMps = speed;
    }

    private double getSpeed(){
        return this.speedInMps;
    }

    @Override
    public void run() {
        try{
            executionStartTime = new Date();
            if (cancel.get()) {
                this.destroy();
                return;
            }

            while (!Thread.interrupted()) {
                long startTime = new Date().getTime();

                if (positionInfo!= null) {
                    if (shouldMove) {
                        moveRunningLocation(); //todo: move to next position
                        positionInfo.setSpeed(speedInMps); //todo 为什么set这个?哪里改过这个?
                    }else{
                        positionInfo.setSpeed(0.0);
                    }
                    positionInfo.setRunnerStatus(this.runnerStatus);

                    final MedicalInfo medicalInfoToUse;
                    switch (this.runnerStatus){ //todo 这个switch很有意思, 这三项执行同一个东西, 已验证
                        case SUPPLY_NOW:
                        case SUPPLY_SOON:
                        case STOP_NOW:
                            medicalInfoToUse = this.medicalInfo;
                            break;
                        default:
                            medicalInfoToUse = null;
                            break;
                    }
                    //todo 将positioninfo转换成currentPosition, 传给distribution服务
                    //所以这里需要一个rest service, 将currentpositoin发送给distribution
                    //todo 为什么要用两个object来做这个事?
                    final CurrentPosition currentPosition = new CurrentPosition(
                            this.positionInfo.getRunningId(),
                            this.positionInfo.getRunnerStatus(),
                            new Point(this.positionInfo.getPosition().getLatitude(),
                                    this.positionInfo.getPosition().getLongitude()),
                            this.positionInfo.getSpeed(),
                            this.positionInfo.getLeg().getHeading(),
                            medicalInfoToUse
                                        );

                    positionService.processPositionInfo(id, currentPosition, this.exportPositionsToMessaging);

                }
                sleep(startTime);
            }




        }catch (InterruptedException e){
            destroy();
            return;
        }
    }

    //还没到需要report的时间段, 所以睡大觉
    private void sleep(long startTime) throws InterruptedException {
        long endTime = new Date().getTime();
        long interval = endTime - startTime;

        long sleepTime =  reportInternal -interval> 0? reportInternal - interval : 0;
        Thread.sleep(sleepTime);

    }

    private void moveRunningLocation(){ //todo move to next location, algorithm is weird...
        double distance = speedInMps * reportInternal / 1000.0;
        double distanceFromStart = distance + positionInfo.getDistanceFromStart();
        double excess = 0.0;

        //todo 这里的逻辑不是很明白
        for (int i = positionInfo.getLeg().getId(); i < legs.size(); i++){
            Leg currentLeg = legs.get(i);
            excess = distanceFromStart - currentLeg.getLength() > 0?distanceFromStart-currentLeg.getLength():0.0;

            if (Double.doubleToRawLongBits(excess) == 0) { //todo 为啥转换为bit?
                positionInfo.setDistanceFromStart(distanceFromStart);
                positionInfo.setLeg(currentLeg);

                Point newPosition = NavUtils.getPosition(currentLeg.getStartPoint(), distanceFromStart, currentLeg.getHeading());
                positionInfo.setPosition(newPosition); // todo set(move) to new location
                return;
            }
            //todo if there is excess: 这个逻辑是什么意思
            distanceFromStart = excess;

        }
        //到达终点, 还想继续模拟, 重置回去:
        setStartPosition();

    }

    public synchronized void  cancel(){ //todo 为什么用synch而不用volatile->看下面帖子
        this.cancel.set(true);
    }
    //todo : https://stackoverflow.com/questions/3786825/volatile-boolean-vs-atomicboolean
    // 新的 todo :那为什么atomic还要加synchronized?好像没有说..继续想



    //定义"thread"的起始状态
    public void setStartPosition(){
        positionInfo = new PositionInfo();
        positionInfo.setRunningId(this.runningId);
        Leg leg = legs.get(0);
        positionInfo.setPosition(leg.getStartPoint());
        positionInfo.setDistanceFromStart(0.0);
    }

    //todo 设置成null以后, ui中就显示它不动了
    void destroy(){
        this.positionInfo = null;
    }
}
