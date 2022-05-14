package fp.charging.points.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * Esta clase define la configuracion de la seguridad en nuestra aplicacion
 * 
 * @author JMTE
 * @since 20/05/2022
 * @version 1.0
 * 
 * 
 * 
 */
@Configuration

/*
 * Para crear una clase de seguridad personalizada, necesitamos usar @EnableWebSecurity y extender
 * la clase con @WebSecurityConfigurerAdapter para que podamos redefinir algunos de los métodos
 * proporcionados.
 */
@EnableWebSecurity
public class WebSecurityData extends WebSecurityConfigurerAdapter {

	/*
	 * La clase necesita un DataSource, clase necesaria para implementar una fuente de Datos
	 * externa(generalmente una base de datos.
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * 
	 * Para encriptar las contraseñas debemos configurar, un @Bean, para poder inyectar en el
	 * controlador un objeto de la clase BCryptPasswordEncoder:
	 * 
	 * @return BCryptPasswordEncoder
	 * 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 
	 * Necesita un método redefinido denominado configure. A través de la variable auth y el siguiente
	 * código auth.jdbcAuthentication().dataSource(dataSource); le indicamos que busque, de forma
	 * automática las tablas predefinidas mediante las query que ponemos en el metodo
	 * 
	 * @param auth
	 * @throws Exception
	 * 
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * usersByUsernameQuery, que es una consulta SQL de las columnas que hay en la tabla, donde le
		 * informo de cual hace de username, password y enable.
		 */

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, enabled from Usuarios where username=?")
				.authoritiesByUsernameQuery(
						"select u.username, p.descripcion from Usuario_Perfiles up " + "inner join Usuarios u on u.username = up.username "
								+ "inner join Perfiles p on p.id_perfil = up.id_Perfil " + "where u.username = ?");

		/*
		 * authoritiesByUsernameQuery, que es una consulta de SQL en donde le digo qué voy a usar como
		 * Username y nombre del perfil de la tabla de perfiles.
		 */
	}

	/**
	 * 
	 * Mediante la clase HttpSecurity tenemos la capacidad de configurar qué URLs están permitidas sin
	 * autorización y qué URLs no lo están.
	 * 
	 * @param http
	 * @throws Exception
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * -autorizeRequest(): con este método se inicia la lista de recursos y urls autorizadas.
		 * -antMatchers(): especificación del recurso o url entre comillas y separados por comas, que
		 * queremos autorizar. -permiteAll() indica que todos los recursos y urls especificados por
		 * antMatchers, están permitidas sin autorización, y por tanto no solicitan la llamada a la url
		 * /login que muestra en el navegador el formulario de Login.
		 * 
		 * 
		 */

		http.csrf().disable().authorizeRequests()
				// Los recursos estáticos no requieren autenticación
				.antMatchers("/bootstrap/**", "/css/**", "js/**", "/img/**").permitAll()

				// Las vistas públicas no requieren autenticación
				.antMatchers("/", "/login", "/logout", "/search", "/registro", "/pwd", "/").permitAll()

				// Asignamos permisos a URLs por ROLES

				.antMatchers("/administrador/**").hasAnyAuthority("ADMIN").antMatchers("/cliente/**").hasAnyAuthority("CLIEN")
				.antMatchers("/empresa/**").hasAnyAuthority("EMPRE")

				// Todas las demÃ¡s URLs de la AplicaciÃ³n requieren autenticaciÃ³n
				.anyRequest().authenticated()

				// El formulario de Login no requiere autenticacion
				// .and().formLogin().permitAll();
				.and().formLogin().loginPage("/login").permitAll();

	}

}
