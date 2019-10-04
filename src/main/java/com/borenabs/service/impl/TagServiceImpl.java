package com.borenabs.service.impl;

import com.borenabs.entity.Tag;
import com.borenabs.mapper.TagMapper;
import com.borenabs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public int deleteByPrimaryKey(Integer tagId) {
        return 0;
    }

    @Override
    public int insert(Tag record) {
        return 0;
    }

    @Override
    public int insertSelective(Tag record) {
        return 0;
    }

    @Override
    public Tag selectByPrimaryKey(Integer tagId) {
        return tagMapper.selectByPrimaryKey(tagId);
    }

    @Override
    public int updateByPrimaryKeySelective(Tag record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Tag record) {
        return 0;
    }

    /**前台获取所有标签*/
    @Override
    public List<Tag> tagList() {
        return tagMapper.tagList();
    }
    /**
     * 获得标签总数
     *
     * @return 数量
     */
    @Override
    public Integer countTag() {
        return tagMapper.countTag();
    }

    /**
     * 查询文章标签By文章Id
     * */
    @Override
    public List<Tag> selectArticleTagListByArticleId(Integer articleId) {
        return tagMapper.selectArticleTagListByArticleId(articleId);
    }

}
