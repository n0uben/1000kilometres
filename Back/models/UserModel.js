module.exports = (sequelize, DataTypes) => {
    const User = sequelize.define("user", {
        id: {
            type: DataTypes.INTEGER,
            allowNull : false
        },
        mail: {
            type: DataTypes.STRING
        },
        password: {
            type: DataTypes.STRING
        },
        pseudo: {
            type: DataTypes.STRING
        },
        admin: {
            type: DataTypes.BOOLEAN
        }
    })

    return User;
}