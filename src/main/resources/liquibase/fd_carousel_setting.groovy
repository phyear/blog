package liquibase

/**
 *
 * @author zhaotianxin
 * @date 2021-04-14 10:30
 **/
databaseChangeLog {
    changeSet(author: "ztxemail@163.com", id: "2021-04-14-create-table-fd-carousel-setting") {
        createTable(tableName: "fd_carousel_setting" ) {
            column(name: "id", type: "BIGINT UNSIGNED", autoIncrement:"true", remarks: "主键"){
                constraints(nullable: false, primaryKey: true)
            }
            column(name: "title", type: "varchar(255)", remarks: "小标题") {
                constraints(nullable: "false")
            }
            column(name: "url", type: "varchar(1000)", remarks: "图片链接") {
                constraints(nullable: "false")
            }
            column(name: "enable", type: "tinyint", remarks: "是否启用", defaultValue: '0' ) {
                constraints(nullable: "false")
            }

            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}