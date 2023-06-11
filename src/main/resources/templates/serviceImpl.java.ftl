package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petshop.common.utils.DateTool;
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
    * ${table.comment!} 服务层实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName},${entity}> implements ${table.serviceName} {


    @Override
    public Boolean add(${entity} ${table.entityPath}) {
        this.save(${table.entityPath});
        return true;
    }

    @Override
    public Boolean update(${entity} ${table.entityPath}) {
        this.updateById(${table.entityPath});
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean updateUsefulByIds(String ids, Boolean flag) {
        //ids  若干个id 用逗号隔开
        String[] aryIds = ids.split(",");
        for(String id: aryIds){

            //查找符合的数据
            UpdateWrapper<${entity}> UpdateWrapper = new UpdateWrapper();
            UpdateWrapper.eq("id",id);

            //修改数据
            ${entity} ${table.entityPath} = this.getOne(UpdateWrapper);
            ${table.entityPath}.setUseful(flag);

            //执行
            this.update(${table.entityPath});
        }
        return true;
    }

    @Override
    public ${entity} getByValue(String value,String name){
    QueryWrapper<${entity}> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(value,name);

        return this.getOne(QueryWrapper);
    }

    @Override
    public List<${entity}> listByValue (String value,String name){
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(value,name);

            return this.list(queryWrapper);
    }

    @Override
    public Page<${entity}> page(Integer pageNum,Integer pageSize,String name) {
        Page<${entity}> page = new Page<>(pageNum,pageSize);
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);

        return this.page(page,queryWrapper);
    }

}