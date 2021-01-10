export interface LoginResponse {
    id: number,
    authenticationToken: string,
    username: string,
    refreshToken: string,
    expiresAt: Date,
    role: string
}