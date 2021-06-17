package liquibase

/**
 *
 * @author zhaotianxin* @date 2021-04-15 19:48
 * */
databaseChangeLog {
    changeSet(author: "ztxemail@163.com", id: "2021-04-15-create-table-tb-article-visits") {
        createTable(tableName: "tb_article_visits" ) {
            column(name: "id", type: "BIGINT UNSIGNED", autoIncrement:"true", remarks: "主键"){
                constraints(nullable: false, primaryKey: true)
            }
            column(name: "article_id", type: "BIGINT UNSIGNED", remarks: "文章Id")
            column(name: "visits_count", type: "BIGINT UNSIGNED", remarks: "访问量")
            column(name: "like_count", type: "BIGINT UNSIGNED", remarks: "点赞数")


            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}
