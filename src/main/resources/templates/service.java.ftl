package ${package.Service};

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};

import java.util.List;

/**
* <p>
    * ${table.comment!} 服务类接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
public interface ${table.serviceName} extends IService<${entity}> {

    Boolean add(${entity} ${table.entityPath});
    void deleteByIds(String ids);
    Boolean update(${entity} ${table.entityPath});
    ${entity} getByValue(String value,String name);
    List<${entity}> listByValue(String value,String name);
    Boolean updateUsefulByIds(String id, Boolean flag);
    Page<${entity}> page(Integer pageNum, Integer pageSize, String name);
}