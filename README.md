声明式服务调用。

RestTemplate调用其他API时，所需要的参数必须在请求的URL中进行拼接，如果参数少的话还可以，但是参数多起来的时候这时拼接请求字符串就会效率低下，并且显的很傻。那么这个时候Netflix的Feign框架就派上用场了。

Feign是一个声明式的WebService客户端。其目的就是让服务调用更加简单。Feign提供了HTTP请求的模板，通过编写简单的接口和插入注解，就可以定义好HTTP请求的参数、格式、地址等信息。而Feign则会完全代理HTTP请求，我们只需要像调用方法一样调用它就可以完成服务请求及相关处理。

Feign整合了Ribbon和Hystrix，可以让我们不再需要显示地使用这两个组件。此外，Spring cloud还对Feign提供了Spring Mvc注解，也使得我们在web中可以使用同一个`HttpMessageConverter`。

总起来说，Feign具有如下特性：

* 可插拔的注解支持，包括Feign注解和JAX-RS注解;
* 支持可插拔的HTTP编码器和解码器;
* 支持Hystrix和它的Fallback;
* 支持Ribbon的负载均衡;
* 支持HTTP请求和响应的压缩。


引入依赖

```
<dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

通过`@EnableFeignClients`开启`Feign`。

然后编写`FeignClient`

```
@FeignClient("SERVICE-HELLO")
public interface UserService {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<User> findAll();

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    User findOne(@PathVariable("id") Integer id);
}
```

> 说明： 假如你的Service是单独编译一个jar包，那么在使用@EnableFeignClients注解时需要指定basePackages的值，如:

```
@EnableFeignClients(basePackages = "com.lanyage.eureka.consumerhello.**")
@EnableDiscoveryClient
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
否则会报错，因为找不到`UserService`的bean。

这里如果是返回的xml的话，那么可以采用如下的方法。
`@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)`

> 继承

当被调用方和调用方有相同的方法声明的时候，可以考虑继承。创建一个公用的项目, 然后创建`UserApiService`。

```
public interface UserApiService {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<User> findAll();

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    User findOne(@PathVariable("id") Integer id);
}
```
然后调用方和被调用方继承就好了。

> 与swagger冲突

如果与swagger集成启动不了，那很可能是你的swagger版本太低。







