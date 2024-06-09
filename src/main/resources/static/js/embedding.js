function send_embedding(prompt) {
    //send request & get response
    const data = {
        prompt: prompt
    };

    fetch(url + `api/embedding?prompt=${prompt}`, {
        method: 'POST',
        headers: {
            'accept': '*/*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}