const axios = require('axios');
const cheerio = require('cheerio');

async function fetchAndProcess() {
    const lengths = [3, 3, 3];
    console.log("מתחיל");

    const path = "https://beneyisrael.com/Tikun-Qorim/cp/cp0309.htm";

    try {
        const response = await axios.get(path);
        const html = response.data;
        const $ = cheerio.load(html);
        const textContents = $('body').text();

        const words1 = textContents.split(/[^א-ת]/);
        const words = words1.filter(word => word.length > 0);

        console.log(words.length);

        for (let i = 0; i < words.length - lengths.length; i++) {
            let match = true;
            for (let j = 0; j < lengths.length; j++) {
                if (words[i + j] == null || words[i + j].length !== lengths[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                const matchedWords = words.slice(i, i + lengths.length).join(' ');
                console.log(matchedWords);
            }
        }
    } catch (error) {
        console.error("Error fetching or processing data:", error);
    }
}

fetchAndProcess();
