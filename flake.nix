{
  description = "matplanleggeren";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    { nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs {
          inherit system;
          overlays = [ ];
        };

        jdk = pkgs.temurin-bin-21;

        commonInputs = with pkgs; [
          chromedriver
          geckodriver
        ];

        jvmInputs = [
          jdk
          pkgs.bleep
          pkgs.scalafmt
        ];

        jsInputs = with pkgs; [ nodejs_20 ];

        shell = ''
          export JAVA_HOME="${jdk}"
        '';

      in
      {
        devShells.default = pkgs.mkShell {
          name = "matplanleggeren-dev-shell";
          buildInputs = commonInputs ++ jvmInputs ++ jsInputs;
          shellHook = shell;
        };
      }
    );
}
