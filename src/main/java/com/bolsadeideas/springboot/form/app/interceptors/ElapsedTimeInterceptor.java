package com.bolsadeideas.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component("elapsedTimeInterceptor")
public class ElapsedTimeInterceptor implements HandlerInterceptor {

    //Para monitorear la op desde la consola
    private static final Logger logger = LoggerFactory.getLogger(ElapsedTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Interceptando: " + handler + "----------");

        if(handler instanceof HandlerMethod) {
            //Si son métodos GET, POST, PUT, etc son handlerMethod
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.info("Entrando al método instancia de HandlerMethod " + handlerMethod.getMethod().getName() + " desde preHandler()----------");

        }

        //Se pasa el initTime al método por el request
        long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);

        //Simulando un delay, OJO el delay se simula antes de entrar al método, en el método no hay delay
        Random random = new Random();
        Integer delay = random.nextInt(1000);
        Thread.sleep(delay);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long endTime = System.currentTimeMillis();
        long initTime = (Long) request.getAttribute("initTime");
        long elapsedTime = endTime - initTime;

        /*
        Esta validación es necesaria ya que el interceptor tambien interceptará los recursos estáticos, y en ellos no existe
        el modelAndView, por lo cual el addObject marcará error en la terminal, no en la aplicación

        El instanceOf haria lo mismo que el modelAndView != null, si no es instancia de uno de los metodos anotados con Mapping, salte el paso
         */
        if(handler instanceof HandlerMethod && modelAndView != null) {
            modelAndView.addObject("elapsedTime", elapsedTime);
        }

        logger.info("Tiempo Transcurrido: " + elapsedTime + " milisegundos");
        logger.info("Saliendo del postHandle() luego de ejecutar el método "+ handler);
    }
}
