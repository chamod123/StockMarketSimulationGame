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
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

function postPlayers(){
	var url="http://localhost:8081/players"
	var data={"name":"Kara"}
	console.log("called post player")
    return new Promise((resolve, reject) => {
        fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Content-Type': 'application/json',
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    })
           .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
				console.log(myJson)
                resolve(myJson)
            })
    })
}
	

export {
    getStocks,
    getMyStocks,
    getPlayers,
    getLeaderBoard,
<<<<<<< HEAD
	postPlayers
=======
    getTransactioHistory
>>>>>>> 5c3183c7571f1e97742d9b79f8b68fde8601f471
}