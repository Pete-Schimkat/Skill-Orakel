module.exports = {
  root: true,
  env: { browser: true, es2020: true },
  extends: [
    'eslint:recommended',
    'plugin:@typescript-eslint/recommended',
    'plugin:react-hooks/recommended',
  ],
  settings: {
    "react": {
      "version": "detect"
    }
  },
  ignorePatterns: ['dist', '.eslintrc.cjs', 'src/generated', 'src/main.tsx' ],
  parserOptions: {
    parser: '@typescript-eslint/parser',
    ecmaVersion: 'latest',
    project: './tsconfig.lint.json',
    tsconfigRootDir: __dirname
  },
  plugins: ['react-refresh', 'react'],
  rules: {
    'react-refresh/only-export-components': [
      'warn',
      { allowConstantExport: true },
    ],
    'react/no-array-index-key': ['error'],
    'react/no-multi-comp': ['error'],
    'react/no-unused-prop-types': ['error'],
    'react/no-unused-state': ['error'],
    'quotes': ['error', 'single', {"avoidEscape": true}],
    '@typescript-eslint/no-floating-promises': ["error"],
    '@typescript-eslint/no-redeclare': ['error'],
    '@typescript-eslint/no-useless-empty-export': ['error'],
    '@typescript-eslint/promise-function-async': ['error'],
    '@typescript-eslint/unified-signatures': ['error'],
    "@typescript-eslint/no-non-null-assertion": "error",
    "@typescript-eslint/switch-exhaustiveness-check": "error",
    "@typescript-eslint/consistent-type-definitions": ["error", "type"]
  },
}
