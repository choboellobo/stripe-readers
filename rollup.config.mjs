import resolve from '@rollup/plugin-node-resolve';
import typescript from '@rollup/plugin-typescript';

export default {
  input: 'src/index.ts',
  external: ['@capacitor/core'],
  output: [
    {
      file: 'dist/plugin.js',
      format: 'iife',
      name: 'capacitorStripeTerminal',
      globals: {
        '@capacitor/core': 'capacitorExports',
      },
      sourcemap: true,
      inlineDynamicImports: true,
    },
    {
      file: 'dist/plugin.cjs.js',
      format: 'cjs',
      sourcemap: true,
      inlineDynamicImports: true,
    },
  ],
  plugins: [
    typescript({
      declaration: true,
      declarationDir: 'dist/esm',
      rootDir: 'src',
    }),
    resolve({
      browser: true,
    }),
  ],
};
