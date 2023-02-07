const dbConfig = require('../config/db-config.js');
const {Sequelize, DataTypes} = require('sequelize');

const sequelize = new Sequelize(
    dbConfig.DB,
    dbConfig.USER,
    dbConfig.PASSWORD, {
        host: dbConfig.HOST,
        dialect: dbConfig.dialect,
        operatorsAliases: false,

        pool: {
            max: dbConfig.pool.max,
            min: dbConfig.pool.min,
            acquire: dbConfig.pool.acquire,
            idle: dbConfig.pool.idle
        }
    }
)

sequelize.authenticate()
.then(() => {
    console.log('db connected ...');
})
.catch(err => {
    console.log('Error db connexion ' + err);
})

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.users = require('./UserModel')(sequelize, DataTypes);
db.cards = require('./CardModel')(sequelize, DataTypes);

db.sequelize.sync({force: false})
.then(() => {
    console.log('Re-sync done !');
})

module.exports = db;