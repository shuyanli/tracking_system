package demo.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//todo 这个的意思是将这个repo暴露给其他service来使用, 而不需要通过我自己的controller接口来访问
//举例: 直接在postman输入 GET http://localhost:9001/supplyLocations , 就能得到所有存的内容(getall)
//GET http://localhost:9001/supplyLocations?page=1&size=10 直接得到分页好的信息
//原因: 父类(PagingAndSortingRepository)中有findAll的两个方法, 直接调用了
@RepositoryRestResource(path = "supplyLocations")
public interface SupplyLocationRepository  extends PagingAndSortingRepository<SupplyLocation, String> {
    SupplyLocation findFirstByLocationNear(@Param("location") Point Location); //todo 为何你们不是findFirstPointNear->如果叫point就得是这个, name matter!!
}

//todo 为什么我们这里没有@Bean这种注入, 而在controller/service里面可以直接用autowire拿来用?
//回答: PagingAndSortingRepository 的父类的父类(curdrepo -> repo)叫做repository interface
//当项目启动的时候, springboot会把所有repository的子类, 继承者全部放进IOC Container