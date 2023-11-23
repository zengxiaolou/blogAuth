#!/bin/bash
set -e

if ! command -v pre-commit &> /dev/null; then
    case "$(uname -s)" in
        Darwin*) brew install pre-commit ;;
        Linux*) sudo apt-get install pre-commit ;;
        CYGWIN*|MINGW32*|MSYS*|MINGW*) choco install pre-commit ;;
        *) echo "unknown OS"; exit 1 ;;
    esac
fi

if ! command -v yq &> /dev/null; then
    case "$(uname -s)" in
        Darwin*) brew install yq ;;
        Linux*) sudo apt-get install yq ;;
        CYGWIN*|MINGW32*|MSYS*|MINGW*) choco install yq ;;
        *) echo "unknown OS"; exit 1 ;;
    esac
fi

if ! pre-commit show >/dev/null 2>&1; then
    pre-commit install --hook-type pre-push
fi

if [ -f .git/hooks/pre-push ]; then
    echo "pre-push hook is installed"
else
    echo "pre-push hook is not installed"
fi

exit 0
