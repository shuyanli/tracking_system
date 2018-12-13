package demo.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//todo 这个的意思是将这个repo暴露给其他service来使用, 而不需要通过我自己的controller接口来访问
//用法: 端口+path
@RepositoryRestResource(path = "supplyLocations")
public interface SupplyLocationRepository  extends PagingAndSortingRepository<SupplyLocation, String> {
    SupplyLocation findFirstByLocationNear(@Param("location") Point Location); //todo 为何你们不是findFirstPointNear
}

//todo 为什么我们这里没有@Bean这种注入, 而在controller/service里面可以直接用autowire拿来用?
//回答: PagingAndSortingRepository 的父类的父类(curdrepo -> repo)叫做repository interface
//当项目启动的时候, springboot会把所有repository的子类, 继承者全部放进IOC Container