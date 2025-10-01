export interface StripeReadersPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
