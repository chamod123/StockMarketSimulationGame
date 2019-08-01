function getStocks() {
    console.log("called  getStocks")// Remove when API is integrated
    return  new Promise((resolve, reject) => { 
        fetch('https://api.myjson.com/bins/vntih')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getMyStocks() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch('https://api.myjson.com/bins/103y91')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getLeaderBoard() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        // fetch('http://localhost:8081/"allPlayers')
        fetch('https://api.myjson.com/bins/1cwg6l')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getTransactioHistory() {
    console.log("called")
    return  new Promise((resolve, reject) => {
        fetch('https://api.myjson.com/bins/1cwg6l')
        //fetch('https://api.myjson.com/bins/1h3uvl')
        // fetch('http://localhost:8081/"allPlayers')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getPlayers() {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch('http://localhost:8081/players/1')
        // fetch('https://api.myjson.com/bins/1azzr5') //test myjson
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

function getPlayer(id) {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8081/players/${id}`)
        // fetch('https://api.myjson.com/bins/1azzr5') //test myjson
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

function postPlayers(data) {
    var url = "http://localhost:8081/players"
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

// GET - get a stock Data
function getStockById(id) {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8081/stock/${id}`)
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

// GET - get stocks Data by sector
function getStocksBySector(id) {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8081/stockBySector/${id}`)
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

// GET - get all transaction Data
{/*function getTransactionHistory() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch('http://localhost:8081/transactions')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}*/
}

// GET - get Bank Balance for a player
function getBankBalance(id) {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/bankBalance${id}`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - get getPortofolio
function getPortofolio(id) {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/portofolio${id}`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - start game
function start() {
    console.log("called start")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/start`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - nextTurn
function nextTurn() {
    console.log("called nextTurn")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/nextTurn`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - winner
function winner() {
    console.log("called winner")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/winner`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}
	

export {
    getStocks,
    getMyStocks,
    getPlayers,
    getPlayer,
    getLeaderBoard,
	postPlayers,
    getTransactioHistory
}