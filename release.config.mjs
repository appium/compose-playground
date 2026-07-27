import semanticReleaseConfig from '@appium/semantic-release-config';

export default semanticReleaseConfig({
  flavor: 'app',
  branches: ['main'],
  extraGitAssets: ['app/build.gradle.kts'],
});
