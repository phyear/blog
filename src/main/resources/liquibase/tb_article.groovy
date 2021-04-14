package liquibase

/**
 *
 * @author zhaotianxin
 * @date 2021-04-14 10:30
 **/
databaseChangeLog {
    changeSet(author: "ztxemail@163.com", id: "2021-04-14-create-table-tb-article") {
        createTable(tableName: "tb_article" ) {
            column(name: "id", type: "BIGINT UNSIGNED", autoIncrement:"true", remarks: "主键"){
                constraints(nullable: false, primaryKey: true)
            }
            column(name: "title", type: "varchar(255)", remarks: "标题") {
                constraints(nullable: "false")
            }
            column(name: "content", type: "text", remarks: "文章内容")
            column(name: "type", type: "varchar(30)", remarks: "类型") {
                constraints(nullable: "false")
            }

            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}