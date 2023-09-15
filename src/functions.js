function getRandomInt(max) {
    coins_given = Math.round(Math.random() * max);
    coins_count += coins_given
    return coins_given;
}

var coins_count = 0