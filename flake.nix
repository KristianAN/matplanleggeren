{
  description = "matplanleggeren";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
    bleepSrc.url = "github:KristianAN/bleep-flake"; # The bleep flake
  };

  outputs =
    {
      nixpkgs,
      flake-utils,
      bleepSrc,
      ...
    }:
    flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs {
          inherit system;
          overlays = [ ];
        };

        jdk = pkgs.temurin-bin-21;
        bleep = bleepSrc.defaultPackage.${system}; # Your bleep system binary

        commonInputs = with pkgs; [
          chromedriver
          geckodriver
        ];

        jvmInputs = [
          jdk
          bleep
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
