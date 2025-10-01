export interface StripeTerminalPlugin {
  /**
   * Inicializa el SDK de Stripe Terminal
   */
  initialize(options: { publishableKey: string }): Promise<void>;

  /**
   * Lista los readers TapToPay disponibles
   */
  discoverReaders(): Promise<{ readers: Reader[] }>;

  /**
   * Detiene la búsqueda de readers
   */
  cancelDiscoverReaders(): Promise<void>;
}

export interface Reader {
  id: string;
  label: string;
  deviceType: string;
  isCharging?: boolean;
  batteryLevel?: number;
  locationId?: string;
}
