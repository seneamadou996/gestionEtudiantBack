package com.amadou.gestionEtudiant.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor implements StatementInspector {
    @Override
    public String inspect(String sql) {
        if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){

            //select utilisateu0_.
            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get("idSchool");
            if (StringUtils.hasLength(entityName)
                    && !entityName.toLowerCase().contains("school")
                    && !entityName.toLowerCase().contains("roles")
                    && StringUtils.hasLength(idEntreprise)){

                if (sql.contains("where")){
                    sql = sql + " and " + entityName + ".idSchool = " + idEntreprise;
                }else {
                    sql = sql + " where " + entityName + ".idSchool = " + idEntreprise;
                }
            }
        }
        return sql;
    }
}
