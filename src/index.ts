import { registerPlugin } from '@capacitor/core';

import type { StripeReadersPlugin } from './definitions';

const StripeReaders = registerPlugin<StripeReadersPlugin>('StripeReaders', {
  web: () => import('./web').then((m) => new m.StripeReadersWeb()),
});

export * from './definitions';
export { StripeReaders };
