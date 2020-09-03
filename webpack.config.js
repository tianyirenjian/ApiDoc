const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const distPath = path.resolve(__dirname, 'src/main/resources/static')
const { VueLoaderPlugin } = require('vue-loader')

module.exports = {
    mode: "development",
    entry: {
        app: ['./src/main/resources/sources/app.js', './src/main/resources/sources/app.css'],
    },
    output: {
        path: distPath,
        filename: '[name].js'
    },
    plugins: [
        new MiniCssExtractPlugin({
            filename: '[name].css'
        }),
        new VueLoaderPlugin()
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            publicPath: distPath
                        }
                    },
                    'css-loader',
                    {
                        loader: 'postcss-loader', options: {
                            ident: 'postcss',
                            plugins: [
                                require('autoprefixer')
                            ]
                        }
                    },
                ],
            }, {
                test: /\.vue$/,
                loader: 'vue-loader'
            }
        ],
    },
    resolve: {
        alias: {
            vue: 'vue/dist/vue.js',
            'root': path.resolve('./src/main/resources/sources')
        }
    }
}