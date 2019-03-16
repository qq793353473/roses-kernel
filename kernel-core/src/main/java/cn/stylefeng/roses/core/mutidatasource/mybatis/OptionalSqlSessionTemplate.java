package cn.stylefeng.roses.core.mutidatasource.mybatis;

import cn.stylefeng.roses.core.mutidatasource.DataSourceContextHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Map;

/**
 * 动态获取SqlSessionFactory，根据DataSourceContextHolder
 *
 * @author fengshuonan
 * @Date 2019/3/16 6:31 PM
 */
public class OptionalSqlSessionTemplate extends SqlSessionTemplate {

    private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;
    private SqlSessionFactory defaultTargetSqlSessionFactory;

    public OptionalSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
        super(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType(),
                new MyBatisExceptionTranslator(
                        sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
        this.targetSqlSessionFactorys = targetSqlSessionFactorys;
        this.defaultTargetSqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        if (targetSqlSessionFactorys == null) {
            throw new IllegalArgumentException("OptionalSqlSessionTemplate初始化时，未初始化targetSqlSessionFactorys！");
        }

        SqlSessionFactory targetSqlSessionFactory = this.targetSqlSessionFactorys.get(DataSourceContextHolder.getDataSourceType());
        if (targetSqlSessionFactory != null) {
            return targetSqlSessionFactory;
        } else if (defaultTargetSqlSessionFactory != null) {
            return defaultTargetSqlSessionFactory;
        }

        return super.getSqlSessionFactory();
    }

}
