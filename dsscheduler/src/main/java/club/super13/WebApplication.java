package club.super13;
/**
 *
 * UCSHOP 版权所有 2017-2018,并保留所有权利。
 *
 * ----------------------------------------------------------------------------
 * --------- 提示：在未取得优势力科技商业授权之前，您不能将本软件应用于商业用途，否则优势力科技将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------
 *
 * 官方网站：http://www.uclee.com
 *
 * Created by heyaopeng on 2017/7/31.
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@MapperScan("club/super13/ds/mapping")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
