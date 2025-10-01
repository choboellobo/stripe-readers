import { WebPlugin } from '@capacitor/core';

import type { StripeReadersPlugin } from './definitions';

export class StripeReadersWeb extends WebPlugin implements StripeReadersPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
