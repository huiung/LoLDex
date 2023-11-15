# LoLDex
Champion dex of League of legends 

<img src="https://github.com/huiung/LoLDex/assets/38308286/d3d9e4bd-16b2-4d78-87b9-c2c55282b066" width="30%"/>
&nbsp;&nbsp;&nbsp;
<img src="https://github.com/huiung/LoLDex/assets/38308286/7277a010-ec06-416e-a872-9077535f1226" width="30%"/>


## Open-source libraries
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Compose : a modern UI framework 
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
- Architecture
  - MVI + Google's official architecture
  - Repository Pattern
- [Material 3](https://m3.material.io/components): Material 3 components.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Coil](https://github.com/coil-kt/coil): An image loading library for Android backed by Kotlin Coroutines (with compose)

## Architecture
LoLDex is based MVI + Google's official architecture guide(https://developer.android.com/topic/architecture) <br>
Therefore, LoLDex has three modules: App -> Domain -> Data

<img src="https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png" width="50%"/>

## RiotAPI(Data Dragon)
<img src="https://static-00.iconduck.com/assets.00/riotgames-icon-249x256-3lpy0d01.png" align="right" width="10%"/>

LoLDex uses [DataDragon](https://developer.riotgames.com/docs/lol#data-dragon) for fetching data related to League of legends. <br>
LoLDex uses DataDragon's [Versions](https://developer.riotgames.com/docs/lol#data-dragon_versions), [Champions](https://developer.riotgames.com/docs/lol#data-dragon_champions) API


# License

<pre>
Designed and developed by 2023 huiung

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
