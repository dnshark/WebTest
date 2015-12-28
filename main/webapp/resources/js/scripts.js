/**
 * Created by Дмитрий on 08.12.2015.
 */

var sec = 00;   // set the seconds
var min = 10;   // set the minutes

function countDown() {
    sec--;
    if (sec == -01) {
        sec = 59;
        min = min - 1;
    }
    else {
        min = min;
    }

    if (sec <= 9) {
        sec = "0" + sec;
    }

    time = (min <= 9 ? "0" + min : min) + " min and " + sec + " sec ";

    if (document.getElementById) {
        document.getElementById('theTime').innerHTML = time;
    }

    SD = window.setTimeout("countDown();", 1000);
    if (min == '00' && sec == '00') {
        sec = "00";
        location.replace("noAnswer");
        window.clearTimeout(SD);
    }
}
window.onload = countDown;

function initTime(time) {
    sec = time % 60;
    min = Math.floor(time / 60);
}