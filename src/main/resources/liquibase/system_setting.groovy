package liquibase

/**
 *
 * @author zhaotianxin
 * @date 2021-04-14 10:30
 **/
databaseChangeLog {
    changeSet(author: "ztxemail@163.com", id: "2021-04-14-create-table-system-setting") {
        createTable(tableName: "system_setting" ) {
            column(name: "id", type: "BIGINT UNSIGNED", autoIncrement:"true", remarks: "主键"){
                constraints(nullable: false, primaryKey: true)
            }
            column(name: "site_name", type: "varchar(255)", remarks: "网站标题") {
                constraints(nullable: "false")
            }
            column(name: "logo", type: "varchar(255)", remarks: "网站logo") {
                constraints(nullable: "false")
            }

            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}