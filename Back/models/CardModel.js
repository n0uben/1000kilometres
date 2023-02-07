module.exports = (sequelize, DataTypes) => {
    const Card = sequelize.define("card", {
        id: {
            type: DataTypes.INTEGER,
            allowNull : false
        },
        name: {
            type: DataTypes.STRING
        },
        km: {
            type: DataTypes.INTEGER
        },
        effect: {
            type: DataTypes.STRING
        },
        image: {
            type: DataTypes.STRING
        },
        noType: {
            type: DataTypes.INTEGER
        }
    })

    return Card;
}