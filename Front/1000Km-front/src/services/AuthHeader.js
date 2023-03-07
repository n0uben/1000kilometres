export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('user'));
    console.log(user)
    if (user && user.authData) {
        console.log('Basic ' + user.authData)
        return 'Basic ' + "TW91bWk6S2F0ekJH";
    } else {
        return '';
    }
}