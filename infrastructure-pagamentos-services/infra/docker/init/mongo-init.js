print('Start #################################################################');
db = db.getSiblingDB('cliente_db');
db.createUser(
  {
    user: 'cliente_user',
    pwd: 'cliente_pass',
    roles: [{ role: 'readWrite', db: 'cliente_db' }],
  }
);
db.createCollection('cliente');

db = db.getSiblingDB('conta_db');
db.createUser(
  {
    user: 'conta_user',
    pwd: 'conta_pass',
    roles: [{ role: 'readWrite', db: 'conta_db' }],
  }
);
db.createCollection('conta');

db = db.getSiblingDB('pagamento_db');
db.createUser(
  {
    user: 'pagamento_user',
    pwd: 'pagamento_pass',
    roles: [{ role: 'readWrite', db: 'pagamento_db' }],
  }
);
db.createCollection('pagamento');

print('END #################################################################');