import { StripeReaders } from 'stripe-readers';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    StripeReaders.echo({ value: inputValue })
}
