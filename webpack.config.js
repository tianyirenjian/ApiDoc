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
                test: /\.(c|sa|sc)ss$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            publicPath: distPath
                        }
                    },
                    // 'style-loader', // 如果 dev server 要使用 css， 则注释上面，并放开这一行 // 也可能不需要
                    'css-loader',
                    'sass-loader',
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
            '/': path.resolve('./src/main/resources/sources/'),
        },
        extensions: ['.js', '.vue']
    },
    devServer: {
        port: 8099,
        progress: true,
        contentBase: "./src/main/resources/static"
    },
    watchOptions: {
        poll: 1000,
        aggregateTimeout: 500,
        ignored: "./node_modules/"
    },
}
