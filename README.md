# @choboellobo/capacitor-stripe-terminal

Plugin de Capacitor para Stripe Terminal que permite listar readers TapToPay en Android.

## Instalación

```bash
npm install @choboellobo/capacitor-stripe-terminal
npx cap sync
```

## Configuración

### Android

Agrega los permisos necesarios en `android/app/src/main/AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
```

## Uso

```typescript
import { StripeTerminal } from '@choboellobo/capacitor-stripe-terminal';

// Inicializar
await StripeTerminal.initialize({
  publishableKey: 'tu_publishable_key_de_stripe'
});

// Buscar readers
const result = await StripeTerminal.discoverReaders();
console.log('Readers encontrados:', result.readers);

// Cancelar búsqueda
await StripeTerminal.cancelDiscoverReaders();
```

## API

### initialize(options)

Inicializa el SDK de Stripe Terminal.

- `options.publishableKey` (string): Tu clave pública de Stripe

### discoverReaders()

Busca readers TapToPay disponibles.

Retorna: `Promise<{ readers: Reader[] }>`

### cancelDiscoverReaders()

Cancela la búsqueda de readers.

## Tipos

```typescript
interface Reader {
  id: string;
  label: string;
  deviceType: string;
  isCharging?: boolean;
  batteryLevel?: number;
  locationId?: string;
}
```
