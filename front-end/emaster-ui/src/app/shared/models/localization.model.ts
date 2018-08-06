export const SupportedLanguages: ILanguage[] = [
    {
        code: 'en',
        display: 'LANG_EN'
    },
    {
        code: 'vn',
        display: 'LANG_VN'
    }
]

export interface ILanguage {
    code: string,
    display: string
}