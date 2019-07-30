function getStocks() {
    console.log("called  getStocks")
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
	

export {
    getStocks,
    getMyStocks,
    getPlayers,
    getLeaderBoard,
	postPlayers,
    getTransactioHistory
}