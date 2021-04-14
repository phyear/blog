package liquibase

/**
 *
 * @author zhaotianxin
 * @date 2021-04-14 10:30
 **/
databaseChangeLog {
    changeSet(author: "ztxemail@163.com", id: "2021-04-14-create-table-fd-user") {
        createTable(tableName: "fd_user" ) {
            column(name: "id", type: "BIGINT UNSIGNED", autoIncrement:"true", remarks: "主键"){
                constraints(nullable: false, primaryKey: true)
            }
            column(name: "login_name", type: "varchar(255)", remarks: "登录名") {
                constraints(nullable: "false")
            }
            column(name: "password", type: "varchar(255)", remarks: "密码") {
                constraints(nullable: "false")
            }

            column(name: "creation_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "last_update_date", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}