// package com.kruglov.websoka.config;
// import java.io.IOException;

// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.stereotype.Component;

// @Component
// public class CorsFilter implements Filter {

//     @Override
//     public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//             throws IOException, ServletException {
//                 if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
//                     HttpServletRequest request = (HttpServletRequest) req;
//                     HttpServletResponse response = (HttpServletResponse) res;
//                     String origin = request.getHeader("Origin");
//                         response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//                         response.setHeader("Vary", "Origin");

//                         // Access-Control-Max-Age
//                         response.setHeader("Access-Control-Max-Age", "3600");

//                         // Access-Control-Allow-Credentials
//                         response.setHeader("Access-Control-Allow-Credentials", "true");

//                         // Access-Control-Allow-Methods
//                         response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

//                         // Access-Control-Allow-Headers
//                         response.setHeader("Access-Control-Allow-Headers",
//                             "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
                    
//                 }
        
//                 chain.doFilter(req, res);
        
//     }

    
// }
