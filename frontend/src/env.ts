import { _throw } from './utils/_throw';

export const env = {
    backendBaseUrl: getEnvVar('VITE_BACKEND_BASE_URL')
}

function getEnvVar(name: string) {
    return import.meta.env[name] ?? _throw(`missing env variable ${name}`);
}

