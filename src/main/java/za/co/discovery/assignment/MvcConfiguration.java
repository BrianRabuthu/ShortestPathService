/*
 * package za.co.discovery.assignment;
 * 
 * import org.springframework.context.annotation.ComponentScan; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.
 * DefaultServletHandlerConfigurer; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc; import
 * org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 * import
 * org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
 * import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 * import org.springframework.web.servlet.view.InternalResourceViewResolver;
 * import org.springframework.web.servlet.view.JstlView;
 * 
 * @Configuration
 * 
 * @EnableWebMvc
 * 
 * @ComponentScan public class MvcConfiguration extends WebMvcConfigurerAdapter
 * {
 * 
 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/resources/**").addResourceLocations(
 * "/resources/"); }
 * 
 * @Override public void
 * configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
 * configurer.enable(); }
 * 
 * @Override public void configureViewResolvers(ViewResolverRegistry registry) {
 * InternalResourceViewResolver resolver = new InternalResourceViewResolver();
 * resolver.setPrefix("/WEB-INF/jsp/"); resolver.setSuffix(".jsp");
 * resolver.setViewClass(JstlView.class); registry.viewResolver(resolver); } }
 * 
 */