package com.borenabs.entity;

public class ArticleWithBLOBs extends Article {
    private String articleContent;

    private String articleSummary;

    public String getArticleContent() {
        return articleContent;
    }

    @Override
    public String toString() {
        return "ArticleWithBLOBs{" +
                "articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                '}';
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary == null ? null : articleSummary.trim();
    }
}