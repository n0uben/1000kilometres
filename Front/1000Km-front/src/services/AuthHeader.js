export default function authHeader() {
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.authData) {
        console.log('Basic ' + user.authData)
        return 'Basic ' + user.authData;
    } else {
        return '';
    }
}