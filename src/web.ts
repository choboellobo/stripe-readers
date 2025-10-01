import { WebPlugin } from '@capacitor/core';

import type { StripeTerminalPlugin, Reader } from './definitions';

export class StripeTerminalWeb extends WebPlugin implements StripeTerminalPlugin {
  async initialize(options: { publishableKey: string }): Promise<void> {
    console.log('initialize', options);
    throw this.unimplemented('Not implemented on web.');
  }

  async discoverReaders(): Promise<{ readers: Reader[] }> {
    throw this.unimplemented('Not implemented on web.');
  }

  async cancelDiscoverReaders(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
}
