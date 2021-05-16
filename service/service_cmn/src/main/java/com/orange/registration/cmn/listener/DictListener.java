package com.orange.registration.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.orange.registration.cmn.mapper.DictMapper;
import com.orange.registration.model.cmn.Dict;
import com.orange.registration.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author Orange
 * @create 2021-05-16 23:02
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;
    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //一行一行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

