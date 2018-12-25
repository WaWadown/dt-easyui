package wo;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

/**
 * @author cailei
 * @date Dec 18, 2018
 */
@SpringBootApplication
//@EntityScan(basePackages = "com.qfedu.psys.po")
@EnableTransactionManagement
// @EnableCaching
public class WoESysApp {

	private final static Logger LOG = LogManager.getLogger(WoESysApp.class);
	
	/**
	 * c3p0配置
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "c3p0")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
	}
	
	// 集成mybatis配置：提供SqlSeesion
	/**
	 * mybatis的sqlSessionFactory
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
		try {
			SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource);
			sessionFactoryBean.setVfs(SpringBootVFS.class);
			// 手写配置
			// 配置类型别名
			sessionFactoryBean.setTypeAliasesPackage("wo.esys.po");

			// 配置mapper的扫描，找到所有的mapper.xml映射文件
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
			sessionFactoryBean.setMapperLocations(resources);
			// 加载全局的配置文件
			// sessionFactoryBean
			// .setConfigLocation(new
			// DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
			// 设置分页插件
			Properties p = new Properties();
			p.setProperty("helperDialect", "mysql");
			p.setProperty("offsetAsPageNum", "true");
			p.setProperty("rowBoundsWithCount", "true");
			p.setProperty("reasonable", "true");
			Interceptor it = new PageInterceptor();
			it.setProperties(p);
			sessionFactoryBean.setPlugins(new Interceptor[] { it });
			return sessionFactoryBean.getObject();
		} catch (IOException e) {
			LOG.warn("mybatis resolver mapper*.xml is error", e);
			return null;
		} catch (Exception e) {
			LOG.warn("mybatis sqlSessionFactoryBean create error");
			return null;
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WoESysApp.class, args);
	}

}
