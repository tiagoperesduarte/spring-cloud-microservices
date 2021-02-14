db.createUser({
    user: "microservices",
    pwd: "microservices",
    roles: [{
        role: "readWrite",
        db: "api"
    }]
});