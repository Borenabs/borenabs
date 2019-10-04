package com.borenabs.mapper;

import com.borenabs.entity.Tag;

import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    /**前台获取所有标签*/
    List<Tag> tagList();
    /**
     * 获得标签总数
     *
     * @return 数量
     */
    Integer countTag();

    /**
     * 查询文章标签By文章Id
     * */
    List<Tag> selectArticleTagListByArticleId(Integer articleId);
}