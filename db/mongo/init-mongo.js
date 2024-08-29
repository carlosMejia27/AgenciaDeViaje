// Seleccionar la base de datos para la inicialización
db = db.getSiblingDB('app_users');

// Crear el usuario 'master' con la contraseña 'debuggeandoideas'
// y asignarle el rol 'readWrite' en la base de datos 'app_users'
db.createUser({
    user: 'master',
    pwd: 'debuggeandoideas',
    roles: [
        {
            role: 'readWrite',
            db: 'app_users',
        },
    ],
});

// Crear una colección llamada 'users' en la base de datos 'app_users'
db.createCollection('users', { capped: false });

// Insertar documentos en la colección 'users'
db.users.insertMany([
    {
        "username": "ragnar777",
        "dni": "VIKI771012HMCRG093",
        "enabled": true,
        "password": "s3cr3t",
        "role": {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "heisenberg",
        "dni": "BBMB771012HMCRR022",
        "enabled": true,
        "password": "p4sw0rd",
        "role": {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "misterX",
        "dni": "GOTW771012HMRGR087",
        "enabled": true,
        "password": "misterX123",
        "role": {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        }
    },
    {
        "username": "neverMore",
        "dni": "WALA771012HCRGR054",
        "enabled": true,
        "password": "4dmIn",
        "role": {
            "granted_authorities": ["ROLE_ADMIN"]
        }
    }
]);
